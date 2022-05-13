(ns speedback.core-test
  (:require [clojure.test :refer :all]
            [speedback.core :as core]))

(deftest generate-round-test
  (testing "return pairs given participants"
    (is (= [[1 5] [2 4] [3 6]]
           (core/generate-round [1 2 3 4 5] 6)))))
