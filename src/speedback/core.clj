(ns speedback.core
  (:gen-class))

(defn generate-round
  [members pivot]
  (let [number-of-pairs (-> members
                            count
                            (/ 2)
                            int
                            inc)
        [group1 group2] (split-at number-of-pairs members)
        group2*         (-> group2
                            reverse
                            vec
                            (conj pivot))]
    (partition 2 (interleave group1 group2*))))

(defn move-around
  [members]
  (let [current-last-member  [(peek members)]
        all-members-but-last (pop members)]

   (concat current-last-member all-members-but-last)))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
