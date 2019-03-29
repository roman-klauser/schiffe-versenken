(ns ships.core)

(def board-size 10)
(defn create-empty-row [] (repeat board-size nil))
(defn create-board [] (repeat board-size (create-empty-row )))
(defn init-game [] (-> (create-board)))
(def board (init-game))

(defrecord Ship [size])
(defrecord Coordinates [x y])

(defn less-or-zero [x] (if (< x 0) 0 x))
(defn less-or-max [x] (if (> x board-size) board-size x))

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
  (let [start (+ (:x coordinates) (* (:y coordinates) board-size))
        end (+ start (* (:size ship) board-size))
        updated (reduce
                  #(assoc %1 %2 "ship")
                  (vec (flatten board)) (range start end board-size))]
    (partition board-size updated)))

(defn get-sector
  [board ship coordinate alignment]
  )

(defn left-top-corner
  [coordinate]
  (let [x (- (:x coordinate) 1)
        y (- (:y coordinate) 1)]
    (Coordinates. (less-or-zero x) (less-or-zero y))))

(defn right-bottom-corner
  [coordinate ship alignment]
  (cond
    (= alignment :horizontal) (Coordinates. (+ (:x coordinate) (:size ship)) (+ (:y coordinate) 1))
    (= alignment :vertical) (Coordinates. (+ (:x coordinate) 1) (+ (:y coordinate) 1 (:size ship)))))


; (0,1) (2,3)

; 0 0 0 0 0 0 0 0 0 0
; x 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 x 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
; 0 0 0 0 0 0 0 0 0 0
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
