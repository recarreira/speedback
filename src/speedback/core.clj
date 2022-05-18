(ns speedback.core
  (:gen-class)
  (:require
   [clojure.string :refer [join split]]))

(defn generate-round-pairs
  [members]
  (let [number-of-pairs  (-> members
                             count
                             (/ 2)
                             int)
        pivot            (peek members)
        members-but-last (pop members)
        [group1 group2]  (split-at number-of-pairs members-but-last)
        group2*          (-> group2
                             reverse
                             vec
                             (conj pivot))]
    (partition 2 (interleave group1 group2*))))

(defn move-around
  [members]
  (let [[last-but-one pivot] (take-last 2 members)
        members-but-last-two (drop-last 2 members)]

    (vec (concat [last-but-one] members-but-last-two [pivot]))))

(defn generate-session-rounds
  [input-members]
  (let [members          (if (odd? (count input-members))
                           (conj input-members "waiting")
                           input-members)
        number-of-rounds (dec (count members))
        process-round    (fn [data _round-number]
                           {:rounds  (conj (:rounds data) (generate-round-pairs (:members data)))
                            :members (move-around (:members data))})]
    (-> (reduce process-round
                {:members members :rounds []}
                (range number-of-rounds))
        :rounds)))

(defn prettify-session
  [session]
  (let [prettify-pairs (fn [round] (join "\n" (map (fn [pair] (join " & " pair)) round)))
        prettify-round (fn [index round] (str "Round " (inc index) ":\n" (prettify-pairs round)))]
   (join "\n\n" (map-indexed prettify-round session))))

(defn -main
  [& args]
  (let [members (split (get (vec args) 0) #",")]
    (println (generate-session-rounds members))))
