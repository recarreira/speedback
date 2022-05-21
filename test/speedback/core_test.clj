(ns speedback.core-test
  (:require [clojure.test :refer :all]
            [speedback.core :as core]
            [schema.test :as schema.test]))

(use-fixtures :once schema.test/validate-schemas)

(deftest generate-round-pairs-test
  (testing "returns pairs given participants"
    (is (= [["1" "5"] ["2" "4"] ["3" "6"]]
           (core/generate-round-pairs ["1" "2" "3" "4" "5" "6"])))
    (is (= [["1" "3"] ["2" "4"]]
           (core/generate-round-pairs ["1" "2" "3" "4"])))))

(deftest move-around-test
  (testing "moves last member to the first position"
    (is (= ["5" "1" "2" "3" "4" "6"]
           (core/move-around ["1" "2" "3" "4" "5" "6"])))))

(deftest generate-session-rounds-test
  (testing "returns all rounds for the session"
    (is (= [[["1" "5"] ["2" "4"] ["3" "6"]]
            [["5" "4"] ["1" "3"] ["2" "6"]]
            [["4" "3"] ["5" "2"] ["1" "6"]]
            [["3" "2"] ["4" "1"] ["5" "6"]]
            [["2" "1"] ["3" "5"] ["4" "6"]]]
           (core/generate-session-rounds ["1" "2" "3" "4" "5" "6"]))))
  (testing "returns rounds for the session with waiting slots given odd number of members"
    (is (= [[["1" "5"] ["2" "4"] ["3" "waiting"]]
            [["5" "4"] ["1" "3"] ["2" "waiting"]]
            [["4" "3"] ["5" "2"] ["1" "waiting"]]
            [["3" "2"] ["4" "1"] ["5" "waiting"]]
            [["2" "1"] ["3" "5"] ["4" "waiting"]]]
           (core/generate-session-rounds ["1" "2" "3" "4" "5"])))))

(deftest prettify-session-test
  (testing "returns readable session separated by rounds and pairs"
    (is (= "Round 1:\n1 - 5\n2 - 4\n3 - 6\n\nRound 2:\n5 - 4\n1 - 3\n2 - 6\n\nRound 3:\n4 - 3\n5 - 2\n1 - 6\n\nRound 4:\n3 - 2\n4 - 1\n5 - 6\n\nRound 5:\n2 - 1\n3 - 5\n4 - 6"
           (core/prettify-session
             [[["1" "5"] ["2" "4"] ["3" "6"]]
              [["5" "4"] ["1" "3"] ["2" "6"]]
              [["4" "3"] ["5" "2"] ["1" "6"]]
              [["3" "2"] ["4" "1"] ["5" "6"]]
              [["2" "1"] ["3" "5"] ["4" "6"]]])))))
