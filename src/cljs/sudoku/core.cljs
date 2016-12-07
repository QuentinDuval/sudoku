(ns sudoku.core
  (:require [reagent.core :as reagent :refer [atom]]))


(enable-console-print!)

;; Contants

(def board-width 9)
(def board-height 9)
(def fill-cell-nb 12)


;; Game initialization

(defn rand-values
  []
  (concat
    (repeatedly fill-cell-nb #(+ 1 (rand-int 9)))
    (repeat 0)
    ))

(defn rand-positions
  []
  (shuffle
    (for [i (range board-width)
          j (range board-height)]
      [i j]
      )))

(defn init-matrix
  "Initialize the game matrix"
  []
  (into {}
    (map vector
      (rand-positions)
      (rand-values)
      )))


;; Game state

(defonce app-state
  (atom {:matrix (init-matrix)}))


;; Drawing the game

(defn rect-cell
  "Draw a rectangle cell on the screen"
  [x y]
  [:rect.cell
   {:x (+ 0.05 x) :width 0.9
    :y (+ 0.05 y) :height 0.9
    :fill "none"
    :stroke-width 0.02
    :stroke "black"}
   ])

(defn text-cell
  "The text to fill inside the rectangle"
  [x y]
  [:text
   {:x (+ 0.5 x) :width 1
    :y (+ 0.72 y) :height 1
    :text-anchor "middle"
    :font-size 0.6}
   (str (get (:matrix @app-state) [x y]))
   ])


(defn render-board
  []
  (into
    [:svg.board
     {:view-box (str "0 0 " board-width " " board-height)
      :style {:max-height "500px"
              ;;:background-color "grey"
              }}]
    (for [i (range board-width)
          j (range board-height)]
      [:g
       [rect-cell i j]
       [text-cell i j]
       ])
    ))

(defn sudoku
  []
  [:div
   [:h1 "Sudoku"]
   [:div [render-board]]
   ])

(reagent/render
  [sudoku]
  (js/document.getElementById "app"))
