(ns culture.facts
  "Country-level regional-culture catalog for Ireland (IRL) -- national
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
  {"IRL"
   [{:culture/id "irl.dish.irish-stew"
     :culture/name "Irish stew"
     :culture/country "IRL"
     :culture/kind :dish
     :culture/summary "Stew from Ireland traditionally made with root vegetables and lamb or mutton, in modern times also commonly made with beef."
     :culture/url "https://en.wikipedia.org/wiki/Irish_stew"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.dish.colcannon"
     :culture/name "Colcannon"
     :culture/country "IRL"
     :culture/kind :dish
     :culture/summary "Traditional Irish dish of mashed potatoes with cabbage, popular on Saint Patrick's Day and the feast day of St. Brigid."
     :culture/url "https://en.wikipedia.org/wiki/Colcannon"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.dish.soda-bread"
     :culture/name "Soda bread"
     :culture/country "IRL"
     :culture/kind :dish
     :culture/summary "Quick bread leavened with sodium bicarbonate instead of yeast, made from flour, baking soda, salt and buttermilk; it originated in the United Kingdom and has strong associations with Irish culinary tradition."
     :culture/url "https://en.wikipedia.org/wiki/Soda_bread"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.dish.boxty"
     :culture/name "Boxty"
     :culture/country "IRL"
     :culture/kind :dish
     :culture/summary "Traditional Irish potato pancake of finely grated raw potato and flour fried on a griddle, associated with the north midlands, Connacht and Ulster."
     :culture/url "https://en.wikipedia.org/wiki/Boxty"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.beverage.guinness"
     :culture/name "Guinness"
     :culture/country "IRL"
     :culture/kind :beverage
     :culture/summary "Stout that originated in the brewery of Arthur Guinness at St. James's Gate, Dublin, Ireland, in the 18th century."
     :culture/url "https://en.wikipedia.org/wiki/Guinness"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.beverage.irish-whiskey"
     :culture/name "Irish whiskey"
     :culture/country "IRL"
     :culture/kind :beverage
     :culture/summary "Distilled spirit made on the island of Ireland from malted cereals, matured at least three years in wooden casks; a protected European geographical indication under Regulation (EC) No 110/2008."
     :culture/url "https://en.wikipedia.org/wiki/Irish_whiskey"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.craft.aran-jumper"
     :culture/name "Aran jumper"
     :culture/country "IRL"
     :culture/kind :craft
     :culture/summary "Cable-knit sweater from the Aran Islands off the west coast of Ireland, traditionally made from cream-colored wool with natural water-resistant properties."
     :culture/url "https://en.wikipedia.org/wiki/Aran_jumper"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.craft.claddagh-ring"
     :culture/name "Claddagh ring"
     :culture/country "IRL"
     :culture/kind :craft
     :culture/summary "Traditional Irish ring whose heart, crown and clasped hands represent love, loyalty and friendship; its modern form originated in the 17th century in the fishing village of Claddagh near Galway."
     :culture/url "https://en.wikipedia.org/wiki/Claddagh_ring"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.festival.saint-patricks-day"
     :culture/name "Saint Patrick's Day"
     :culture/country "IRL"
     :culture/kind :festival
     :culture/summary "Religious and cultural holiday held on 17 March, the traditional death date of Saint Patrick, the foremost patron saint of Ireland, celebrated in Ireland and worldwide."
     :culture/url "https://en.wikipedia.org/wiki/Saint_Patrick%27s_Day"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irl.heritage.bru-na-boinne"
     :culture/name "Brú na Bóinne"
     :culture/country "IRL"
     :culture/kind :heritage
     :culture/summary "Neolithic monument complex in County Meath, Ireland, built around 3300 BC and including the passage tombs of Newgrange, Knowth and Dowth; a UNESCO World Heritage Site designated in 1993."
     :culture/url "https://en.wikipedia.org/wiki/Br%C3%BA_na_B%C3%B3inne"
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
      :note (str "cloud-itonami-iso3166-irl culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "IRL"))
                 " IRL entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
