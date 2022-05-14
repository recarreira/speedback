(ns speedback.core-test
  (:require [clojure.test :refer :all]
            [speedback.core :as core]))

(deftest generate-round-pairs-test
  (testing "returns pairs given participants"
    (is (= [[1 5] [2 4] [3 6]]
           (core/generate-round-pairs [1 2 3 4 5 6])))
    (is (= [[1 3] [2 4]]
           (core/generate-round-pairs [1 2 3 4])))))

(deftest move-around-test
  (testing "moves last member to the first position"
    (is (= [5 1 2 3 4 6]
           (core/move-around [1 2 3 4 5 6])))))

(deftest generate-session-rounds-test
  (testing "returns all rounds for the session"
    (is (= [[[1 5] [2 4] [3 6]]
            [[5 4] [1 3] [2 6]]
            [[4 3] [5 2] [1 6]]
            [[3 2] [4 1] [5 6]]
            [[2 1] [3 5] [4 6]]]
           (core/generate-session-rounds [1 2 3 4 5 6])))))
