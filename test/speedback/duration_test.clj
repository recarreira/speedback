(ns speedback.duration-test
  (:require
   [clojure.test :refer :all]
   [speedback.duration :as duration]))

(deftest suggested-duration-test
  (testing "returns the suggested total duration given number of participants"
    (is (= 59
           (duration/suggested-duration 6)))))
