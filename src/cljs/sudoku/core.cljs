(ns sudoku.core
  (:require [reagent.core :as reagent :refer [atom]]))


(enable-console-print!)

(def board-width 9)
(def board-height 9)


(defn init-matrix
  "Initialize the game matrix"
  []
  (into {}
    (for [i (range board-width)
          j (range board-height)]
      [[i j] 0]
      )))

(defonce app-state
  (atom {:matrix (init-matrix)}))


(defn rect-cell
  "Draw a rectangle cell on the screen"
  [x y]
  [:rect.cell
   {:x (+ 0.05 x) :width 0.9
    :y (+ 0.05 y) :height 0.9
    :fill "none"
    :stroke-width 0.01
    :stroke "black"}
   ])


(defn render-board
  []
  (into
    [:svg.board
     {:view-box (str "0 0 " board-width " " board-height)
      :style {:max-height "500px"}}]
    (for [i (range board-width)
          j (range board-height)]
      [rect-cell i j]
      )))

(defn sudoku
  []
  [:div
   [:h1 "Sudoku"]
   [:div [render-board]]
   ])

(reagent/render
  [sudoku]
  (js/document.getElementById "app"))
