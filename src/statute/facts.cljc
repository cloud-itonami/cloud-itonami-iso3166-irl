(ns statute.facts
  "General-law compliance catalog for Ireland (IRL) -- a 45th
  country-level entry (see cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/-fra/
  -can/-aus/-kor/-nld/-ita/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/
  -chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd/-ind/-ken/-tha/
  -are/-vnm/-idn/-phl/-egy/-tur/-nga/-sau/-mys/-aut/-che for the first
  forty-four) per ADR-2607141700 (cloud-itonami-compliance-fact-federation).

  Reuses this tick-window's already-verified capital-status finding
  from cloud-itonami-municipality-irl-dublin (tick 131): Dublin is
  Ireland's stable capital, with no ongoing ambiguity.

  Ireland's official statute database (irishstatutebook.ie) and
  gov.ie returned HTTP 403 for the municipality entries at tick 131,
  so this tick used alternate official/professional sources.

  Companies Act 2014 (No. 38 of 2014) -- commencement date (1 June
  2015) directly confirmed via enterprise.gov.ie (Ireland's
  Department of Enterprise, Trade and Employment, the official
  government department responsible for company law), which states
  verbatim: 'The Act came into effect on 1 June, 2015.' The exact Act
  number was cross-confirmed via en.wikipedia.org's own infobox
  ('Companies Act 2014 (No. 38 of 2014)'), which separately notes the
  Act was signed into law on 23 December 2014 (the signing
  President's name incidentally encountered but never persisted
  here) -- :enacted-date uses the later commencement date, the point
  at which the Act actually took legal effect, matching this
  session's established pattern (e.g. Nigeria's NDPA, Malaysia's
  PDPA).

  Data Protection Act 2018 -- directly confirmed via DLA Piper's
  'Data Protection Laws of the World' resource (the same source used
  successfully for Saudi Arabia's PDPL at tick 120 and Switzerland's
  FADP at tick 129), which states verbatim: 'The Irish Data
  Protection Act 2018 (\"DP Act\") came into force on 25 May 2018 in
  order to give further effect to the GDPR in Ireland.' A specific
  Act number (commonly cited elsewhere as No. 7 of 2018) could not be
  directly confirmed via any successfully-rendered page this tick
  (irishstatutebook.ie 403'd, and no dedicated Wikipedia article
  exists at the expected title), so :law-number uses only the
  directly-confirmed citation title rather than an unverified number.

  An entry not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "ISO3166 alpha-3 -> vector of statute entries."
  {"IRL"
   [{:statute/id "irl.companies-act-2014"
     :statute/title "Companies Act 2014"
     :statute/jurisdiction "IRL"
     :statute/kind :law
     :statute/law-number "No. 38 of 2014"
     :statute/url "https://enterprise.gov.ie/en/what-we-do/company-corporate-law/companies-act-2014/"
     :statute/url-provenance :official-enterprise-gov-ie
     :statute/enacted-date "2015-06-01"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "irl.data-protection-act-2018"
     :statute/title "Data Protection Act 2018"
     :statute/jurisdiction "IRL"
     :statute/kind :law
     :statute/law-number "Data Protection Act 2018"
     :statute/url "https://www.dlapiperdataprotection.com/index.html?t=law&c=IE"
     :statute/url-provenance :dlapiper-data-protection-laws-of-the-world
     :statute/enacted-date "2018-05-25"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis [jurisdiction] (get catalog jurisdiction))

(defn coverage
  ([] (coverage (keys catalog)))
  ([jurisdictions]
   (let [have (filter catalog jurisdictions)
         missing (remove catalog jurisdictions)]
     {:requested (count jurisdictions)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-irl statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "IRL")) " Ireland entries seeded "
                 "with enterprise.gov.ie/dlapiperdataprotection.com citations. "
                 "Extend `statute.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [jurisdiction topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis jurisdiction)))
