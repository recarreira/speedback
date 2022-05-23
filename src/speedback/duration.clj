(ns speedback.duration
  (:require [schema.core :as s]
            [speedback.aux :as aux]))

(def Durations
  {:total        s/Int
   :introduction s/Int
   :pair         s/Int
   :swap         s/Int})

(def default-introduction-time 5)

(def default-swap-time 1)

(s/defn ^:private calculates-total-swap-time :- s/Int
  [number-of-rounds :- s/Int]
  (* default-swap-time (dec number-of-rounds)))

(s/defn suggested-duration :- s/Int
  [number-of-members :- s/Int]
  (let [number-of-rounds (aux/calculates-number-of-rounds number-of-members)
        total-swap-time  (calculates-total-swap-time number-of-rounds)
        time-per-pair    10]
    (-> number-of-rounds
        (* time-per-pair)
        (+ default-introduction-time)
        (+ total-swap-time))))

(s/defn durations-by-total-time :- Durations
  [total-time :- s/Int
   number-of-participants :- s/Int]
  (let [number-of-rounds (aux/calculates-number-of-rounds number-of-participants)
        total-swap-time (calculates-total-swap-time number-of-rounds)
        pair-time (-> total-time
                      (- default-introduction-time)
                      (- total-swap-time)
                      (/ number-of-rounds)
                      (int))]
    {:total total-time
     :introduction default-introduction-time
     :swap default-swap-time
     :pair pair-time}))
