(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 220000 :monthly-rate 15000 :monitoring-months 6 :claimed-fee 310000.0}]
    (is (== 310000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 220000 :monthly-rate 15000 :monitoring-months 6 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "TLS" 0)
        s (registry/register-submit "eng-1" "TLS" 0)]
    (is (= "TLS-DFT-000000" (get d "draft_number")))
    (is (= "TLS-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "TLS" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest local-works-procedure-satisfied-not-invoked
  (testing "an engagement that does not invoke the procedure is always satisfied"
    (is (true? (registry/local-works-procedure-satisfied?
                {:uses-local-works-procedure? false :tender-value 9999999 :fraud-or-corruption-sanctioned? true})))))

(deftest local-works-procedure-satisfied-within-ceiling-and-clean
  (is (true? (registry/local-works-procedure-satisfied?
              {:uses-local-works-procedure? true :tender-value 200000 :fraud-or-corruption-sanctioned? false}))))

(deftest local-works-procedure-unsatisfied-over-ceiling
  (is (false? (registry/local-works-procedure-satisfied?
               {:uses-local-works-procedure? true :tender-value 300000 :fraud-or-corruption-sanctioned? false}))))

(deftest local-works-procedure-unsatisfied-when-sanctioned
  (is (false? (registry/local-works-procedure-satisfied?
               {:uses-local-works-procedure? true :tender-value 1000 :fraud-or-corruption-sanctioned? true}))))
