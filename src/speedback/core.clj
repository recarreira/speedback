(ns speedback.core
  (:gen-class)
  (:require
   [clojure.string :refer [join split]]
   [schema.core :as s]
   [speedback.duration :as duration]))

(def Member s/Str)

(def Pair [(s/one Member "first member")
           (s/one Member "second member")])

(def Round [Pair])

(def Session [Round])

(s/defn generate-round-pairs :- Round
  [members :- [Member]]
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

(s/defn move-around :- [Member]
  [members :- [Member]]
  (let [[last-but-one pivot] (take-last 2 members)
        members-but-last-two (drop-last 2 members)]

    (vec (concat [last-but-one] members-but-last-two [pivot]))))

(s/defn generate-session-rounds :- Session
  [input-members :- [Member]]
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

(s/defn prettify-session :- s/Str
  [session :- Session]
  (let [prettify-pairs (fn [round] (->> round
                                        (map #(join " - " %))
                                        (join "\n")))
        prettify-round (fn [index round]
                         (str "Round " (inc index) ":\n" (prettify-pairs round)))]
   (->> session
        (map-indexed prettify-round)
        (join "\n\n"))))

(defn -main
  [& args]
  (let [members (split (get (vec args) 0) #",")]
    (-> members
        generate-session-rounds
        prettify-session
        println)
    (-> members
        count
        duration/suggested-duration
        duration/prettify-duration
        println)))
