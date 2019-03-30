(ns ships.core)

(def board-size 10)
(defn create-empty-row [] (repeat board-size nil))
(defn create-board [] (repeat board-size (create-empty-row )))
(defn init-game [] (-> (create-board)))
(def board (init-game))

(defrecord Ship [size])
(defrecord Coordinates [x y])

(defn more-or-zero [c] (if (< c 0) 0 c))
(defn less-or-max [c] (if (>= c board-size) (- board-size 1) c))

(defn set-ship
  [board ship coordinate alignment]
  (let [start (+ (* (:y coordinate) board-size) (:x coordinate))
        end (cond
              (= alignment :horizontal) (+ start (:size ship))
              (= alignment :vertical) (+ start (* (:size ship) board-size)))
        ship-on-board (cond
               (= alignment :horizontal) (range start end)
               (= alignment :vertical) (range start end board-size))
        updated (reduce #(assoc %1 %2 "ship") (vec (flatten board)) ship-on-board)]
    (partition board-size updated)))


(defn valid-horizontal-placement?
  [ship coordinates]
  (<= (+ (:x coordinates) (:size ship)) board-size))

(defn valid-vertical-placement?
  [ship coordinates]
  (<= (+ (:y coordinates) (:size ship)) board-size))

(defn put-ship-horizontally
  [board ship coordinates]
  (when (valid-horizontal-placement? ship coordinates)
    (let [start (+ (:x coordinates) (* (:y coordinates) board-size))
          end (+ start (:size ship))
          updated (reduce
                    #(assoc %1 %2 "ship")
                    (vec (flatten board)) (range start end))]
      (partition board-size updated))))

(defn put-ship-vertically
  [board ship coordinates]
  (when (valid-vertical-placement? ship coordinates)
    (let [start (+ (:x coordinates) (* (:y coordinates) board-size))
          end (+ start (* (:size ship) board-size))
          updated (reduce
                    #(assoc %1 %2 "ship")
                    (vec (flatten board)) (range start end board-size))]
      (partition board-size updated))))

(defn get-sector
  [board ship coordinate alignment]
  )

(defn left-top-corner
  [coordinate]
  (->Coordinates (more-or-zero (- (:x coordinate) 1))
                 (more-or-zero (- (:y coordinate) 1))))

(defn right-bottom-corner
  [ship coordinate alignment]
  (cond
    (= alignment :horizontal)
      (->Coordinates (less-or-max (+ (:x coordinate) (:size ship)))
                     (less-or-max (+ (:y coordinate) 1)))
    (= alignment :vertical)
      (->Coordinates (less-or-max (+ (:x coordinate) 1))
                     (less-or-max (+ (:y coordinate)  (:size ship))))))

(defn foo
  [ship coordinate alignment]
  [(left-top-corner coordinate)
   (right-bottom-corner ship coordinate alignment)])

; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 x 0
; 0 0 0 0 0 0 0 0 x 0
; 0 0 0 0 0 0 0 0 x 0
; 0 0 0 0 0 0 0 0 x 0
(defn fire
  [board row-idx col-idx]
  (get-in (vec board) [row-idx col-idx]))

(defn -main
  [& args]
  ())

; FLATTEN
; conj
; mapcat



; Because [lists] are linked lists,
; they do not support efficient random access;
; thus, nth on a list will run in linear time
; (as opposed to constant time when used with vectors, arrays, and so on),
; and get does not support lists at all because doing so would not align with get’s objective of sublinear efficiency.
