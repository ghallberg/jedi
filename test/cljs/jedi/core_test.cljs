(ns jedi.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [jedi.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
