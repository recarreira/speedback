(ns speedback.duration
  (:require [schema.core :as s]
            [speedback.aux :as aux]
            [speedback.duration :as duration]))

(def Durations
  {:total        s/Int
   :introduction s/Int
   :pair         s/Int
   :swap         s/Int})

(def default-introduction-time 5)
(def default-swap-time 1)
(def default-pair-time 10)


(s/defn ^:private calculates-total-swap-time :- s/Int
  [number-of-rounds :- s/Int]
  (* default-swap-time (dec number-of-rounds)))

(s/defn suggested-duration :- Durations
  [number-of-members :- s/Int]
  (let [number-of-rounds (aux/calculates-number-of-rounds number-of-members)
        total-swap-time  (calculates-total-swap-time number-of-rounds)
        total-duration   (-> number-of-rounds
                            (* default-pair-time)
                            (+ default-introduction-time)
                            (+ total-swap-time))]
    {:total total-duration
     :introduction default-introduction-time
     :swap default-swap-time
     :pair default-pair-time}))

(s/defn prettify-duration :- s/Str
  [durations :- Durations]
  (format "\nTotal duration - %dmin\nGiven:\nIntroduction - %dmin\nTime per pair - %dmin\nTime switching pairs - %dmin per round"
          (:total durations)
          (:introduction durations)
          (:pair durations)
          (:swap durations)))

(s/defn ^:private validate-pair-time
  [pair-time :- s/Int]
  (when (< pair-time 1) (throw (Exception. "Not enough time"))))

(s/defn durations-by-total-time :- Durations
  [total-time :- s/Int
   number-of-participants :- s/Int]
  (let [number-of-rounds (aux/calculates-number-of-rounds number-of-participants)
        total-swap-time (calculates-total-swap-time number-of-rounds)
        pair-time (-> total-time
                      (- default-introduction-time)
                      (- total-swap-time)
                      (quot number-of-rounds))]
    (validate-pair-time pair-time)
    {:total total-time
     :introduction default-introduction-time
     :swap default-swap-time
     :pair pair-time}))
