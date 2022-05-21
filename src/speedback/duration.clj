(ns speedback.duration)

(defn suggested-duration
  [number-of-members]
  (let [number-of-rounds  (dec number-of-members)
        swap-time         (dec number-of-rounds)
        time-per-pair     10
        introduction-time 5]
    (-> number-of-rounds
        (* time-per-pair)
        (+ introduction-time)
        (+ swap-time))))
