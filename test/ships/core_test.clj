(ns ships.core-test
  (:require [clojure.test :refer :all]
            [ships.core :refer :all]))


(def empty-board '(
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    (nil nil nil nil nil nil nil nil nil nil)
                    ))

(deftest should-create-an-empty-board
  (is (= (create-empty-row) '(nil nil nil nil nil nil nil nil nil nil))))

(deftest should-create-an-empty-board
  (is (= (create-board) empty-board)))

(deftest should-put-ship-horizontally-a
  (is (= (put-ship-horizontally empty-board
                                (->Ship 4)
                                (->Coordinates 0 0))
         '(
            ("ship" "ship" "ship" "ship" nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            ))))

(deftest should-put-ship-horizontally-b
  (is (= (put-ship-horizontally empty-board
                                (->Ship 2)
                                (->Coordinates 2 4))
         '(
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil "ship" "ship" nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            ))))

(deftest should-put-ship-horizontally-invalid
  (is (= (put-ship-horizontally empty-board
                                (->Ship 3)
                                (->Coordinates 8 4))
         nil)))

(deftest should-put-ship-vertically-a
  (is (= (put-ship-vertically empty-board
                                (->Ship 4)
                                (->Coordinates 0 0))
         '(
            ("ship" nil nil nil nil nil nil nil nil nil)
            ("ship" nil nil nil nil nil nil nil nil nil)
            ("ship" nil nil nil nil nil nil nil nil nil)
            ("ship" nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            ))))

(deftest should-put-ship-vertically-b
  (is (= (put-ship-vertically empty-board
                              (->Ship 2)
                              (->Coordinates 3 5))
         '(
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil "ship" nil nil nil nil nil nil)
            (nil nil nil "ship" nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            ))))

(deftest should-put-ship-vertically-invalid
  (is (= (put-ship-vertically empty-board
                              (->Ship 3)
                              (->Coordinates 3 7))
         '(
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil "ship" nil nil nil nil nil nil)
            (nil nil nil "ship" nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            (nil nil nil nil nil nil nil nil nil nil)
            ))))

(deftest should-place-a-ship-horizonally
  (let [ship (->Ship 3)
        coordinate (->Coordinates 1 0)]
    (is (= (set-ship empty-board ship coordinate :horizontal)
           '(
              (nil "ship" "ship" "ship" nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              )))))

(deftest should-place-a-ship-horizonally
  (let [ship (->Ship 4)
        coordinate (->Coordinates 1 0)]
    (is (= (set-ship empty-board ship coordinate :vertical)
           '(
              (nil "ship" nil nil nil nil nil nil nil nil)
              (nil "ship" nil nil nil nil nil nil nil nil)
              (nil "ship" nil nil nil nil nil nil nil nil)
              (nil "ship" nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              (nil nil nil nil nil nil nil nil nil nil)
              )))))