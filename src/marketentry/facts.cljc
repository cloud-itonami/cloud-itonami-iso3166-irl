(ns marketentry.facts "Ireland market-entry catalog.")
(def catalog
  {"IRL" {:name "Ireland"
          :owner-authority "OGP / eTenders"
          :legal-basis "Public Procurement Regulations; EU directives"
          :national-spec "eTenders supplier registration + CRO number"
          :provenance "https://www.etenders.gov.ie/"
          :required-evidence ["CRO company number record" "eTenders registration record" "CRO extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / OGP"
          :rep-legal-basis "EU establishment or Irish CRO entity for many procedures"
          :rep-provenance "https://www.etenders.gov.ie/"
          :corporate-number-owner-authority "Companies Registration Office / Revenue"
          :corporate-number-legal-basis "CRO number / tax reference"
          :corporate-number-provenance "https://www.cro.ie/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
