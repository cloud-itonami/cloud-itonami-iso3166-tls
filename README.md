# cloud-itonami-iso3166-tls

**`:implemented`** for **TLS** (Timor-Leste): an "Actors" pattern
market-entry / public-procurement compliance service (Governor + LLM
advisor + langgraph-clj StateGraph + append-only audit ledger + Store).

Flagship check: `local-works-procedure-ineligible` (Decreto-Lei n.º
2/2010, de 18 de Fevereiro -- a decentralized civil-works procurement
track for contracts up to $250,000, gated on a value ceiling and a
fraud/corruption debarment bar). **Seven** governor checks total.

```
kbb -M:dev:test
```

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor. Timor-Leste
(independence 2002) has genuinely thin digitized legal infrastructure
compared to most other iso3166 siblings in this fleet -- every citation
below was independently fetched and read this session (2026-07-23),
mostly via `curl` + `pdftotext` against government-hosted PDFs of the
Jornal da República (Timor-Leste's own official gazette), never
paraphrased from training-data memory.

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites
  `timor-leste.gov.tl`'s own legislation index for Decreto-Lei n.º
  10/2005 (Regime Jurídico do Aprovisionamento, the base procurement
  law, confirmed current via its own Seventh Amendment, Decreto-Lei
  n.º 5/2021) and Decreto-Lei n.º 16/2017 (Registo Comercial, SERVE's
  exclusive commercial-registration competence). `governor.cljc`'s
  flagship check (`local-works-procedure-ineligible`) independently
  verifies BOTH a $250,000 value ceiling AND a fraud/corruption
  debarment bar for engagements invoking Decreto-Lei n.º 2/2010's
  decentralized civil-works procurement track -- see the governor's
  own namespace docstring for exactly which of that decree's six
  admissibility conditions this does, and deliberately does not,
  enforce as a hard gate (a district-based preference is NOT modeled
  as a hard bar, because the law's own text frames it as a scoring
  preference, not an admissibility requirement).
- `src/statute/facts.cljk` -- general-law catalog: Lei n.º 10/2017
  (Nova Lei das Sociedades Comerciais, which this iteration confirmed
  directly from its own primary text revokes the prior Lei n.º
  4/2004), Lei n.º 15/2017 (Lei do Investimento Privado), and Lei n.º
  8/2008 (Lei Tributária). **Labor law is a disclosed, honest gap** --
  this iteration made a sustained effort (the ILO's own NATLEX
  database returned a genuine bot-detection HTTP 403 this session and
  was NOT bypassed, per this fleet's hard rule; SEPFOPE's domain did
  not resolve; the government portal has no labor-law category; UNCTAD
  returned a server error) and could not independently verify a
  current Timor-Leste labor-law citation -- see `statute.facts`'s
  namespace docstring for the full trail. No labor-law entry was added
  rather than guess one.

Every citation is `curl`/`pdftotext`-verified against an official
`.gov.tl` source (`timor-leste.gov.tl`, `serve.gov.tl`) -- every
downloaded PDF's own Jornal da República masthead (Série/N.°/date) is
visible in the extracted text, confirming these are the government's
own primary documents. See `src/marketentry/facts.cljk`'s namespace
docstring for the full research trail, including facts this iteration
could NOT verify (the national e-procurement portal's own content,
behind a JS-rendered page this session; a single-day date discrepancy
between DL 16/2017's own text and a secondary ADB database entry for
the predecessor 2006 registration code) and honestly disclosed rather
than invented.

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Timor-Leste:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
