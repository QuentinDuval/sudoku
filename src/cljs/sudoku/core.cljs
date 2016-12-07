(ns sudoku.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"}))

(defn sudoku
  []
  [:h1 (:text @app-state)])

(reagent/render
  [sudoku]
  (js/document.getElementById "app"))
