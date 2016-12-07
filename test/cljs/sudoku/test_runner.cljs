(ns sudoku.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [sudoku.core-test]
   [sudoku.common-test]))

(enable-console-print!)

(doo-tests 'sudoku.core-test
           'sudoku.common-test)
