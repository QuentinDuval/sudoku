(ns sudoku.common-test
  #? (:cljs (:require-macros [cljs.test :refer (is deftest testing)]))
  (:require [sudoku.common :as sut]
            #?(:clj [clojure.test :refer :all]
               :cljs [cljs.test])))

(deftest example-passing-test-cljc
  (is (= 1 1)))
