(ns culture.facts
  "Country-level regional-culture catalog for Timor-Leste (TLS) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"TLS"
   [{:culture/id "tls.dish.batar-daan"
     :culture/name "Batar Da'an"
     :culture/country "TLS"
     :culture/kind :dish
     :culture/summary "Popular East Timorese dish of corn, mung beans, and pumpkin, per the Wikipedia article on the cuisine of Timor-Leste."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Timor-Leste"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tls.dish.budu"
     :culture/name "Budu"
     :culture/country "TLS"
     :culture/kind :dish
     :culture/summary "East Timorese sauce of tomato, mint, lime, and Spanish onion, per the Wikipedia article on the cuisine of Timor-Leste."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Timor-Leste"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tls.dish.ikan-sabuko"
     :culture/name "Ikan Sabuko"
     :culture/country "TLS"
     :culture/kind :dish
     :culture/summary "East Timorese dish of Spanish mackerel in tamarind marinade with basil and capsicum, per the Wikipedia article on the cuisine of Timor-Leste."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Timor-Leste"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tls.beverage.coffee"
     :culture/name "Timor-Leste coffee"
     :culture/country "TLS"
     :culture/kind :beverage
     :culture/summary "Timor-Leste's largest non-oil export; the country is the largest single-source organic coffee producer globally, grown on volcanic soils at high tropical altitude."
     :culture/url "https://en.wikipedia.org/wiki/Coffee_industry_of_Timor-Leste"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tls.craft.tais"
     :culture/name "Tais"
     :culture/country "TLS"
     :culture/kind :craft
     :culture/summary "Traditional weaving created by the women of East Timor, used for ceremonial adornment and as a symbol of national and cultural identity."
     :culture/url "https://en.wikipedia.org/wiki/Tais"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tls.heritage.cristo-rei-dili"
     :culture/name "Cristo Rei of Dili"
     :culture/country "TLS"
     :culture/kind :heritage
     :culture/summary "27-metre-high statue of Jesus Christ at Cape Fatucama in Dili, one of Timor-Leste's primary tourist attractions and a symbol of national identity since independence in 2002."
     :culture/url "https://en.wikipedia.org/wiki/Cristo_Rei_of_Dili"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tls culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "TLS"))
                 " TLS entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
