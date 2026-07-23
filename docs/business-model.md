# Business Model: Independent Public-Sector Market-Entry & Procurement Compliance Service — Timor-Leste

## Classification

- Repository: `cloud-itonami-iso3166-tls`
- ISO 3166: `TLS` (Democratic Republic of Timor-Leste)
- Activity: public-procurement market-entry and ongoing regulatory-
  compliance navigation for an already-incorporated operator
- Social impact: [:local-development-participation :public-spend-transparency :cross-border-friction-reduction]

## Customer

- an already-incorporated `cloud-itonami-cofog-{code}` /
  `cloud-itonami-isco-{code}` / `cloud-itonami-unspsc-{segment}` /
  `cloud-itonami-{ISIC}` operator wanting to bid on a Timor-Leste
  public contract
- a foreign SME or civic-tech vendor entering the public sector in
  Timor-Leste for the first time
- a `cloud-itonami-M6910` client that has just completed incorporation
  and now needs public-sector market access
- a locally-based small/medium contractor wanting to use Decreto-Lei
  n.º 2/2010's decentralized civil-works procurement track for
  contracts up to $250,000

## Offer

- registration walkthrough for SERVE (Serviço de Registo e Verificação
  Empresarial, I.P.), Timor-Leste's exclusive business-registration
  authority (Decreto-Lei n.º 16/2017)
- corporate-form compliance checklist under the Lei das Sociedades
  Comerciais (Lei n.º 10/2017)
- tax-registration checklist under the Lei Tributária (Lei n.º 8/2008)
- public-procurement filing navigation under the Regime Jurídico do
  Aprovisionamento (Decreto-Lei n.º 10/2005, as amended)
- eligibility screening for Decreto-Lei n.º 2/2010's decentralized
  local-works procurement track (value ceiling + fraud/corruption
  debarment check)
- ongoing regulatory-change monitoring subscription
- compliance-audit export package for the client's own records

## Revenue

- per-engagement market-entry fee (one-time registration + checklist
  completion)
- recurring regulatory-change monitoring subscription
- compliance-audit export package

## Trust Controls

- any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off (`:filing/submit` is never automated at any phase)
- a false or fabricated regulatory-requirement claim is a HARD hold that
  cannot be overridden by human approval alone — it must be corrected
  against a cited official source first
- this service does **not** provide legal or tax advice; characterization
  and filing on the client's behalf beyond checklist/draft assistance
  routes to Timor-Leste-licensed counsel or a registered agent
- every requirement cites the official portal or regulation, never
  invented — where this iteration could not independently verify a
  requirement (labor law), the gap is disclosed in `statute.facts`
  rather than filled with an invented citation

## Boundary with adjacent actors (read before forking)

- **`com-etzhayyim-ooyake`** (etzhayyim/root): read-only civic-wayfinding
  mirror of government structure, non-commercial, barred from acting as
  or for the government (G3 impersonation ban). This blueprint is
  commercial and never claims to be an official channel.
- **`matsurigoto`** (etzhayyim/root): sovereign e-government statecraft —
  literally the government, for etzhayyim's own covenant or an adopting
  nation-state. This blueprint is an independent operator the government
  contracts with or that bids into its procurement — never the
  government.
- **`com-etzhayyim-toritsugi`** (etzhayyim/root): guides a consenting
  INDIVIDUAL citizen through their OWN procedure, non-profit,
  donation-only. This blueprint's client is a business operator, not an
  individual citizen, and it is commercial.
- **`legal-entity.etzhayyim.com`**: read-only aggregated company-registry
  data, no execution. This blueprint executes (gated) registrations.
- **`cloud-itonami-M6910`**: helps a client BECOME a legal entity
  (incorporation, ISIC 6910) — a prior, different regulatory phase
  (company law). This blueprint assumes incorporation is already done and
  handles public-procurement market entry (a different regulatory domain).
- **`cloud-itonami-cofog-{code}`**: a jurisdiction-agnostic operator
  template for ONE public function. This blueprint is the orthogonal
  jurisdiction-specific axis — the two compose (fork a COFOG-function
  blueprint AND this one to operate in Timor-Leste).
