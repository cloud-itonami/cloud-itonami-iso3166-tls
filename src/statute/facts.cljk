(ns statute.facts
  "General-law compliance catalog for the Democratic Republic of
  Timor-Leste (TLS) -- extends this repo's existing `marketentry.facts`
  (public-procurement market-entry only, narrow scope) with a second,
  orthogonal catalog of statutes a company operating in this
  jurisdiction must generally track for compliance. Mirrors
  cloud-itonami-iso3166-idn/-moz/-gnb's `statute.facts` (ADR-2607141700,
  cloud-itonami-compliance-fact-federation).

  Timor-Leste (independence 2002) has genuinely thin digitized legal
  infrastructure. Every entry below cites a government-hosted PDF of
  the Jornal da República (Timor-Leste's own official gazette) that
  this iteration actually downloaded with `curl` and read with
  `pdftotext` directly this session (2026-07-23) -- never fabricated,
  never paraphrased from training-data memory. Each PDF's own masthead
  (\"Jornal da República\", the issue's own Série/N.°/date) is visible
  in the extracted text, confirming these are the government's own
  primary documents, not scans or third-party mirrors:

  - **Lei do Investimento Privado (Private Investment Law)**: this
    iteration found, on `timor-leste.gov.tl`'s own 'Empresas e
    Investimento' legislation index (`?cat=29`), a direct government-
    hosted PDF link, downloaded and `pdftotext`-verified directly (own
    primary text, verbatim masthead: \"Jornal da República,
    Quarta-Feira, 23 de Agosto de 2017, Série I, N.° 33\"), confirming,
    in the law's own words: \"LEI N.º 15/2017 de 23 de Agosto -- LEI DO
    INVESTIMENTO PRIVADO\".
  - **Lei Tributária (Tax Law)**: found on the SAME government index's
    'Impostos' category (`?cat=30`), downloaded and `pdftotext`-verified
    directly (own primary text, verbatim masthead: \"Jornal da
    República, Segunda-Feira, 30 de Junho de 2008, Série I, N.° 26\"),
    confirming, in the law's own words: \"LEI N.º 8/2008 de 30 de
    Julho -- LEI TRIBUTÁRIA\" (the gazette's own SUMÁRIO section dates
    it 30 de Junho, while the law's own printed header inside the
    document says 30 de Julho -- this iteration does not silently pick
    one; both are quoted here exactly as each appears in the primary
    source, and the SUMÁRIO date is treated as authoritative for the
    `:statute/enacted-date` field below since it matches the issue's
    own cover-page date). The same government index also lists a
    predecessor instrument, Lei n.º 5/2002, de 20 de Setembro (Lei de
    Modificação do Sistema Tributário) -- this iteration did NOT
    independently fetch and read a revocation clause proving 5/2002 is
    fully superseded by 8/2008 (an honest gap; both exist in the
    government's own index, but only 8/2008's own text was directly
    verified this session).
  - **Lei das Sociedades Comerciais (Companies Law)**: SHARED with
    `marketentry.facts`'s business-registration entry -- see there for
    the full citation trail. Downloaded directly from SERVE's own
    government site and `pdftotext`-verified (own primary text,
    verbatim masthead: \"Jornal da República, Quarta-Feira, 17 de Maio
    de 2017, Série I, N.° 19\"), confirming, in the law's own words:
    \"LEI N.º 10/2017 de 17 de Maio -- NOVA LEI DAS SOCIEDADES
    COMERCIAIS\", and, in its own preamble: \"revoga integralmente a
    Lei n.º 4/2004, sobre Sociedades Comerciais\" (fully revokes Lei
    n.º 4/2004).

  **Labor law -- HONEST GAP, not filled.** This iteration made a
  genuine, sustained effort to independently verify Timor-Leste's
  current labor/labour law (a \"Lei do Trabalho\" or equivalent Labour
  Code) via primary or credible secondary sources this session, and
  could NOT do so:

  - `timor-leste.gov.tl`'s own legislation navigation menu has NO
    \"Trabalho\" (Labor) category -- only Empresas e Investimento,
    Impostos, Imigração, Importação/Exportação, Petróleo e Gás,
    Transportes e Comunicações, and Constituição (all seven confirmed
    directly from the site's own navigation).
  - The ILO's own NATLEX legal database
    (`webapps.ilo.org/dyn/natlex/natlex4.byCountry2?p_country=TLS`)
    returned an HTTP 403 bot-detection response to a direct fetch this
    session. Per this fleet's own hard rule against bypassing bot
    detection, this iteration did NOT attempt any workaround (no
    browser automation, no header spoofing beyond a standard user
    agent) -- it instead tried the Internet Archive Wayback Machine as
    the mandated fallback, which had NO snapshot for that specific
    NATLEX query URL.
  - SEPFOPE (Secretária de Estado da Formação Profissional e do
    Emprego -- Timor-Leste's own employment/training secretariat,
    whose full institutional name this iteration DID independently
    confirm via a primary source, Decreto-Lei n.º 1/2010's own text,
    which lists it as a working-group member) has no resolvable
    `sepfope.gov.tl` domain this session (DNS `NXDOMAIN`); the only
    Wayback Machine snapshot found for that domain is a bare 302
    redirect from 2014, with no usable content.
  - Timor-Leste's own Lei do Investimento Privado (Lei n.º 15/2017),
    whose full 40-page text this iteration downloaded and read
    directly, references worker-related obligations only in general
    terms (\"acordos coletivos de trabalho aplicáveis\" -- applicable
    collective labor agreements; \"segurança no trabalho\" -- workplace
    safety) without citing a specific labor-law instrument by number.
  - The UNCTAD Investment Policy Hub's Timor-Leste page returned an
    HTTP 500 server error (not a bot-detection block) to a direct
    fetch this session.

  Per this vertical's own non-negotiable rule (never fabricate a
  citation), this iteration adds NO labor-law entry to `catalog` below
  rather than guess a plausible-sounding decree number. A future
  iteration with a working ILO NATLEX or SEPFOPE fetch path should add
  one -- do not backfill this gap from training-data memory.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url."
  )

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"TLS"
   [{:statute/id "tls.lei-das-sociedades-comerciais-2017"
     :statute/title "Nova Lei das Sociedades Comerciais (Companies Law)"
     :statute/jurisdiction "TLS"
     :statute/kind :law
     :statute/law-number "Lei n.º 10/2017, de 17 de Maio (own text, Jornal da República Série I, N.° 19, own preamble: 'revoga integralmente a Lei n.º 4/2004, sobre Sociedades Comerciais')"
     :statute/url "https://serve.gov.tl/wp-content/uploads/2022/08/SERIE_I_NO_19_LEI-10_2017-LEI-DAS-SOCIDADES-COMERCIAIS.pdf"
     :statute/url-provenance :official-serve-gov-tl
     :statute/enacted-date "2017-05-17"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "tls.lei-do-investimento-privado-2017"
     :statute/title "Lei do Investimento Privado (Private Investment Law)"
     :statute/jurisdiction "TLS"
     :statute/kind :law
     :statute/law-number "Lei n.º 15/2017, de 23 de Agosto"
     :statute/url "https://timor-leste.gov.tl/wp-content/uploads/2021/03/SERIE_I_NO_331.pdf"
     :statute/url-provenance :official-timor-leste-gov-tl
     :statute/enacted-date "2017-08-23"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:investment}}
    {:statute/id "tls.lei-tributaria-2008"
     :statute/title "Lei Tributária (Tax Law)"
     :statute/jurisdiction "TLS"
     :statute/kind :law
     :statute/law-number "Lei n.º 8/2008 (own text, Jornal da República Série I, N.° 26, SUMÁRIO cover-page date 30 de Junho de 2008; the law's own printed in-document header says 30 de Julho -- both dates quoted honestly, see namespace docstring)"
     :statute/url "https://timor-leste.gov.tl/wp-content/uploads/2010/03/Lei_2008_8_Lei_Tributaria_1.pdf"
     :statute/url-provenance :official-timor-leste-gov-tl
     :statute/enacted-date "2008-06-30"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tls statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "TLS")) " TLS statutes seeded with "
                 "official-source citations (company, investment, tax). "
                 "Labor law is a KNOWN, DISCLOSED gap this iteration could "
                 "not independently verify (ILO NATLEX bot-blocked, "
                 "SEPFOPE domain unreachable, no gov.tl labor category) -- "
                 "see the namespace docstring. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
