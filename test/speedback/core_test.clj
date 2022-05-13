(ns speedback.core-test
  (:require [clojure.test :refer :all]
            [speedback.core :as core]))

(deftest generate-round-test
  (testing "return pairs given participants"
    (is (= [[1 5] [2 4] [3 6]]
           (core/generate-round [1 2 3 4 5] 6)))
    (is (= [[1 3] [2 4]]
           (core/generate-round [1 2 3] 4)))))

(deftest move-around-test
  (testing "move last member to the first position"
    (is (= [5 1 2 3 4]
           (core/move-around [1 2 3 4 5])))))
