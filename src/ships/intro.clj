(ns ships.intro)

(defrecord Person [fname lname address accounts])

(defrecord Address [street city zip])

(defrecord Account [iban])

(def peter (Person.
             "peter"
             "lustig"
             (Address. "street a" "city a" 123)
             '((Account. "DE1234567890") (Account. "US1234567890") (Account. "DE9876543210"))
             ))

(def petra (Person.
             "petra"
             "lustig"
             (Address. "street b" "city a" 123)
             '((Account. "DE1234567890") (Account. "US1234567891"))
             ))

(defn blank? [str]
  (every? #(Character/isWhitespace %) str))

(defn foo [pred col]
  (first (keep-indexed (fn [i e] (when (pred e) i)) col)))