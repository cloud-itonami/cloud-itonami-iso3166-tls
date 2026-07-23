(ns marketentry.sim
  "Demo driver -- `clojure -M:dev:run`. Walks a clean engagement
  through intake -> jurisdiction assessment -> filing draft
  (escalate/approve/commit) -> filing submit (escalate/approve/
  commit), then shows HARD-hold scenarios."
  (:require [langgraph.graph :as g]
            [marketentry.store :as store]
            [marketentry.operation :as op]))

(def operator {:actor-id "op-1" :actor-role :market-entry-operator :phase 3})

(defn- exec-op [actor tid request context]
  (g/run* actor {:request request :context context} {:thread-id tid}))

(defn- approve! [actor tid]
  (g/run* actor {:approval {:status :approved :by "op-1"}} {:thread-id tid :resume? true}))

(defn -main [& _]
  (let [db (store/seed-db)
        actor (op/build db)]
    (println "== engagement/intake eng-1 (TLS, clean) ==")
    (println (exec-op actor "t1" {:op :engagement/intake :subject "eng-1"
                                  :patch {:id "eng-1" :operator "Dili Uma Kmanek Lda"}} operator))

    (println "== jurisdiction/assess eng-1 (escalates -- human approves) ==")
    (println (exec-op actor "t2" {:op :jurisdiction/assess :subject "eng-1"} operator))
    (println (approve! actor "t2"))

    (println "== filing/draft eng-1 (always escalates -- actuation/draft-filing) ==")
    (let [r (exec-op actor "t3" {:op :filing/draft :subject "eng-1"} operator)]
      (println r)
      (println "-- human market-entry operator approves --")
      (println (approve! actor "t3")))

    (println "== filing/submit eng-1 (always escalates -- actuation/submit-filing) ==")
    (let [r (exec-op actor "t4" {:op :filing/submit :subject "eng-1"} operator)]
      (println r)
      (println "-- human market-entry operator approves --")
      (println (approve! actor "t4")))

    (println "== jurisdiction/assess eng-2 (no spec-basis -> HARD hold) ==")
    (println (exec-op actor "t5" {:op :jurisdiction/assess :subject "eng-2" :no-spec? true} operator))

    (println "== jurisdiction/assess eng-2 (sets up serve-registration-missing) ==")
    (println (exec-op actor "t5b" {:op :jurisdiction/assess :subject "eng-2"} operator))
    (println (approve! actor "t5b"))
    (println (exec-op actor "t5c" {:op :filing/draft :subject "eng-2"} operator))
    (println (approve! actor "t5c"))
    (println "== filing/submit eng-2 (serve-registration-missing -> HARD hold) ==")
    (println (exec-op actor "t6" {:op :filing/submit :subject "eng-2"} operator))

    (println "== jurisdiction/assess eng-3 (sets up local-works-procedure-ineligible: value ceiling) ==")
    (println (exec-op actor "t7" {:op :jurisdiction/assess :subject "eng-3"} operator))
    (println (approve! actor "t7"))
    (println (exec-op actor "t7b" {:op :filing/draft :subject "eng-3"} operator))
    (println (approve! actor "t7b"))
    (println "== filing/submit eng-3 (tender-value $340,000 > $250,000 ceiling -> HARD hold, FLAGSHIP) ==")
    (println (exec-op actor "t8" {:op :filing/submit :subject "eng-3"} operator))

    (println "== jurisdiction/assess eng-4 (sets up local-works-procedure-ineligible: fraud/corruption) ==")
    (println (exec-op actor "t9" {:op :jurisdiction/assess :subject "eng-4"} operator))
    (println (approve! actor "t9"))
    (println (exec-op actor "t9b" {:op :filing/draft :subject "eng-4"} operator))
    (println (approve! actor "t9b"))
    (println "== filing/submit eng-4 (fraud-or-corruption-sanctioned? -> HARD hold, FLAGSHIP) ==")
    (println (exec-op actor "t10" {:op :filing/submit :subject "eng-4"} operator))

    (println "== jurisdiction/assess eng-5 (sets up engagement-fee-mismatch) ==")
    (println (exec-op actor "t11" {:op :jurisdiction/assess :subject "eng-5"} operator))
    (println (approve! actor "t11"))
    (println (exec-op actor "t11b" {:op :filing/draft :subject "eng-5"} operator))
    (println (approve! actor "t11b"))
    (println "== filing/submit eng-5 (fee mismatch -> HARD hold) ==")
    (println (exec-op actor "t12" {:op :filing/submit :subject "eng-5"} operator))

    (println "== jurisdiction/assess eng-6 (does not use local-works procedure -- conditional branch) ==")
    (println (exec-op actor "t13" {:op :jurisdiction/assess :subject "eng-6"} operator))
    (println (approve! actor "t13"))
    (println (exec-op actor "t13b" {:op :filing/draft :subject "eng-6"} operator))
    (println (approve! actor "t13b"))
    (println "== filing/submit eng-6 (clean, no local-works procedure invoked -> escalate then commit) ==")
    (let [r (exec-op actor "t14" {:op :filing/submit :subject "eng-6"} operator)]
      (println r)
      (println (approve! actor "t14")))

    (println "== filing/draft eng-1 AGAIN (double-draft -> HARD hold) ==")
    (println (exec-op actor "t15" {:op :filing/draft :subject "eng-1"} operator))

    (println "== filing/submit eng-1 AGAIN (double-submit -> HARD hold) ==")
    (println (exec-op actor "t16" {:op :filing/submit :subject "eng-1"} operator))

    (println "== audit ledger ==")
    (doseq [f (store/ledger db)] (println f))

    (println "== draft records ==")
    (doseq [r (store/draft-history db)] (println r))

    (println "== submit records ==")
    (doseq [r (store/submit-history db)] (println r))))
