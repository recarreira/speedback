(ns speedback.duration-test
  (:require
   [clojure.test :refer :all]
   [speedback.duration :as duration]))

(deftest suggested-duration-test
  (testing "returns the suggested total duration given even number of participants"
    (is (= {:total 59
            :introduction 5
            :swap 1
            :pair 10}
           (duration/suggested-duration 6))))
  (testing "returns the suggested total duration given odd number of participants"
    (is (= {:total 59
            :introduction 5
            :swap 1
            :pair 10}
           (duration/suggested-duration 5)))))

(deftest prettify-duration-test
  (testing "returns explained suggested duration as string"
    (is (= "\nThe total duration of this session is 59min, given:\n5min introduction\n10min exchanging feedback per pair\n1min switching beetween pairs"
           (duration/prettify-duration {:total 59
                                        :introduction 5
                                        :pair 10
                                        :swap 1})))))

(deftest durations-by-total-time-test
  (testing "returns the duration per pair given total time and number participants"
   (is (= {:total 59
           :introduction 5
           :swap 1
           :pair 10}
          (duration/durations-by-total-time 59 6))))
  (testing "returns the rounded duration per pair"
   (is (= 10
        (:pair (duration/durations-by-total-time 60 6)))))
  (testing "throws an exception when pair time is less than 1"
    (is (thrown-with-msg? Exception #"Not enough time")
        (duration/durations-by-total-time 4 6))))
