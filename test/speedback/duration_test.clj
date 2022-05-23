(ns speedback.duration-test
  (:require
   [clojure.test :refer :all]
   [speedback.duration :as duration]))

(deftest suggested-duration-test
  (testing "returns the suggested total duration given even number of participants"
    (is (= 59
           (duration/suggested-duration 6))))
  (testing "returns the suggested total duration given odd number of participants"
    (is (= 59
           (duration/suggested-duration 5)))))

(deftest durations-by-total-time-test
  (testing "returns the duration per pair given total time and number participants"
   (is (= {:total 59
           :introduction 5
           :swap 1
           :pair 10}
          (duration/durations-by-total-time 59 6))))
  (testing "returns the rounded duration per pair"
   (is (= 10
          (:pair (duration/durations-by-total-time 60 6))))))
