(ns speedback.core
  (:gen-class))

(defn generate-round
  [members pivot]
  (let [number-of-pairs (-> members
                            count
                            (/ 2)
                            int
                            inc)
        [group1 group2] #nu/tapd (split-at number-of-pairs members)
        group2* (-> group2
                    reverse
                    vec
                    (conj pivot))]
    (partition 2 (interleave group1 group2*))))

(defn move-around
  [members]
  (concat [(peek members)] (pop members)))






(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
