(ns sudoku.core
  (:require [reagent.core :as reagent :refer [atom]]))


(enable-console-print!)

;; Contants

(def board-width 9)
(def board-height 9)
(def fill-cell-nb 12)


;; Game initialization
;; TODO - This might generate invalid sudoku board!

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
;; TODO - Plug this with modal window

(defonce app-state
  (atom
    {:matrix (init-matrix)
     :selecting false
     }))


;; Drawing the game
;; TODO - Try different colors based on which square it is
;; TODO - Pop up a window:
;; http://stackoverflow.com/questions/35709165/how-to-do-modal-dialogs-with-om-or-reagent-and-bootstrap
;;

(defn rect-cell
  "Draw a rectangle cell on the screen"
  [x y]
  [:rect.cell
   {:x (+ 0.05 x) :width 0.9
    :y (+ 0.05 y) :height 0.9
    :fill "white"
    :stroke-width 0.025
    :on-click #(println "titi")
    :stroke "black"}
   ])

(defn text-cell
  "The text to fill inside the rectangle"
  [x y]
  [:text
   {:x (+ 0.5 x) :width 1
    :y (+ 0.72 y) :height 1
    :text-anchor "middle"
    :on-click #(println "toto")
    :font-size 0.6}
   (str (get (:matrix @app-state) [x y]))
   ])

(defn render-board
  "Render the board of the sudoku"
  []
  (into
    [:svg.board
     {:view-box (str "0 0 " board-width " " board-height)
      :shape-rendering "auto"
      :style {:max-height "500px"}}]
    (for [i (range board-width)
          j (range board-height)]
      [:g
       [rect-cell i j]
       [text-cell i j]]
      )))

(defn sudoku
  "Main rendering function"
  []
  [:div
   [:h1 "Sudoku"]
   [:div [render-board]]
   ])

(reagent/render
  [sudoku]
  (js/document.getElementById "app"))
