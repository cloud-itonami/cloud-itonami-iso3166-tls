(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Timor-Leste procurement law, whether a claimed SERVE
  (Serviço de Registo e Verificação Empresarial) commercial-registration
  record is actually on file, whether a claimed engagement fee actually
  equals base + months x rate, whether a company invoking the
  decentralized local-works procurement track actually qualifies for
  it, or when a draft stops being a draft and becomes a real-world
  filing submission, so this MUST be a separate system able to *reject*
  a proposal and fall back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints; this is the TLS-family
  implementation of that governor).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  SEVEN checks, in priority order, ALL HARD violations: a human
  approver CANNOT override them. The confidence/actuation gate is
  SOFT: it asks a human to look (low confidence / actuation), and the
  human may approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. SERVE registration missing  -- for `:filing/submit`, when the
                                       engagement declares
                                       `:requires-serve-registration?
                                       true`, INDEPENDENTLY verify
                                       `:has-serve-registration?` is
                                       true. Grounded in Decreto-Lei
                                       n.º 16/2017, de 17 de Maio,
                                       Artigo 3.º (Competência): SERVE,
                                       I.P. has EXCLUSIVE commercial-
                                       registration competence since
                                       2012 (see `marketentry.facts`).
    4. Local-works-procedure
       ineligible                  -- for `:filing/submit`, when the
                                       engagement declares
                                       `:uses-local-works-procedure?
                                       true` (the client wants to bid
                                       via Decreto-Lei n.º 2/2010's
                                       decentralized, sub-district-scale
                                       civil-works procurement track),
                                       INDEPENDENTLY verify BOTH (a) the
                                       engagement's own declared
                                       `:tender-value` does not exceed
                                       $250,000, AND (b) the engagement
                                       is NOT `:fraud-or-corruption-
                                       sanctioned?`. FLAGSHIP genuinely
                                       new check for the iso3166 family
                                       (grep-verified absent as a
                                       governor check function name
                                       fleet-wide at build time) --
                                       see the discussion below for
                                       exactly what this check does and
                                       does NOT enforce, and why.
    5. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    6. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate. (SOFT -- see above.)

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value) -- these plus items 1-5 above are the SEVEN HARD violation
  functions `check` actually concatenates.

  ---------------------------------------------------------------------
  What the flagship check does, and deliberately does NOT, enforce
  ---------------------------------------------------------------------

  This governor's `local-works-procedure-violations` is grounded
  directly in Decreto-Lei n.º 2/2010, de 18 de Fevereiro (own primary
  text, fetched and `pdftotext`-verified this session -- see
  `marketentry.facts` for the full provenance trail), Artigo 2.º
  (\"Requisitos para admissibilidade das empresas\"), which lists SIX
  numbered admissibility conditions. This check deliberately implements
  only TWO of those six as HARD governor conditions, and explicitly
  does NOT implement a third that a less careful reading could
  mistake for a hard gate:

  - Implemented as a HARD condition: the decree's own SCOPE is bounded
    to civil-works contracts \"de valor até $USD 250.000\" (up to
    $250,000) -- its own title and Artigo 1.º frame the entire special
    procedure around this ceiling. A company cannot legitimately invoke
    this decentralized, lighter-weight track for a contract outside
    that scope, so this check independently verifies
    `:tender-value` <= 250000 whenever `:uses-local-works-procedure?`
    is true.
  - Implemented as a HARD condition: Artigo 2.º §4, own text, verbatim:
    \"Não é admissível a participação de determinada empresa, quando à
    mesma tenha sido aplicada sanção por fraude ou corrupção.\" (A
    given company's participation is NOT admissible when it has been
    sanctioned for fraud or corruption.) This is an unambiguous
    admissibility BAR, so this check independently verifies
    `:fraud-or-corruption-sanctioned?` is not true.
  - Deliberately NOT implemented as a hard condition: Artigo 2.º §3,
    own text, verbatim: \"É dada preferência às empresas com sede ou
    com trabalhos realizados ou em realização no distrito onde o
    projecto vai ser realizado.\" (Preference IS GIVEN to companies
    headquartered in, or with works done or being done in, the
    district where the project will occur.) The decree's own words are
    \"é dada preferência\" -- a PREFERENCE / scoring weight applied
    during the district joint team's classification process (Artigos
    3.º-6.º), NOT an admissibility bar. A company based outside the
    project's district remains legally admissible under this decree's
    own text; it simply scores lower in the classification. Modeling
    this as a hard governor HOLD would misrepresent the law -- so this
    governor does not.
  - A further, separate honest note on terminology: this decree's own
    TITLE reads \"...Empresas Locais Sediadas nos Sub-Distritos\" (Local
    Companies Based in the Sub-Districts), but every OPERATIVE article
    that actually states the locality criterion (Artigo 1.º §3, Artigo
    2.º §3, Artigo 4.º alínea b) ii) uses \"distrito\" (district), not
    \"sub-distrito\" (sub-district) -- Timor-Leste's actual second-tier
    administrative division is the sub-district (\"sub-distrito\"),
    nested within a district (\"distrito\"), so these are NOT
    interchangeable terms. This governor's docstrings and this
    namespace's own function names say \"local-works-procedure\"
    precisely to avoid silently picking one of \"district\"/
    \"sub-district\" and asserting it as what the operative law
    requires when the primary text itself is inconsistent between its
    title and its own articles."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real filing package and submitting a real portal/filing
  registration are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

(def local-works-value-ceiling
  "Decreto-Lei n.º 2/2010's own scope ceiling, in USD: 'valor até
  $USD 250.000'."
  250000)

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(SERVE商業登記/会社法遵守/納税記録/調達申請等)が充足していない状態での提案"}]))))

(defn- serve-registration-missing-violations
  "For `:filing/submit`, when the engagement declares
  `:requires-serve-registration? true`, INDEPENDENTLY verify
  `:has-serve-registration?` is true -- grounded in Decreto-Lei n.º
  16/2017, Artigo 3.º: SERVE has EXCLUSIVE commercial-registration
  competence. CONDITIONAL on the engagement's own
  `:requires-serve-registration?` ground truth."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-serve-registration? e))
                 (not (true? (:has-serve-registration? e))))
        [{:rule :serve-registration-missing
          :detail (str subject " はSERVE(Serviço de Registo e Verificação Empresarial)への"
                      "商業登記(Decreto-Lei n.º 16/2017 Artigo 3.º)を要するが未確認 -- 提出提案は進められない")}]))))

(defn- local-works-procedure-violations
  "For `:filing/submit`, when the engagement declares
  `:uses-local-works-procedure? true` (invoking Decreto-Lei n.º
  2/2010's decentralized civil-works procurement track), INDEPENDENTLY
  verify BOTH the value-ceiling condition and the fraud/corruption bar
  -- the TLS flagship check. See the namespace docstring for exactly
  which of Artigo 2.º's six admissibility conditions this does, and
  deliberately does not, enforce as a hard gate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (true? (:uses-local-works-procedure? e))
        (cond
          (and (some? (:tender-value e))
               (> (:tender-value e) local-works-value-ceiling))
          [{:rule :local-works-procedure-ineligible
            :detail (str subject " の入札価値(" (:tender-value e)
                        ")がDecreto-Lei n.º 2/2010の適用上限($USD "
                        local-works-value-ceiling ")を超過 -- この簡易手続は使用不可")}]

          (true? (:fraud-or-corruption-sanctioned? e))
          [{:rule :local-works-procedure-ineligible
            :detail (str subject " は詐欺・汚職により制裁を受けている(Artigo 2.º §4) -- "
                        "Decreto-Lei n.º 2/2010の特別手続への参加は不可")}])))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (serve-registration-missing-violations request st)
                           (local-works-procedure-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
