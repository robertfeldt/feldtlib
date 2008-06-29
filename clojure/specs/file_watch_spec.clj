(spec keys-with-different-values
  (spec "empty maps"
    (is = [] (keys-with-different-values {} {}))
  )
  (spec "one empty map and one with a single key-value pair"
    (is = [:k] (keys-with-different-values {:k 1} {}))
    (is = [:k] (keys-with-different-values {} {:k 2}))
  )
  (spec "one pair in both with different keys"
    (is = [:k1 :k2] (keys-with-different-values {:k1 1} {:k2 3}))
  )
  (spec "one pair in both with same key and same value"
    (is = [] (keys-with-different-values {:k \a} {:k \a}))
  )
  (spec "one pair in both with same key and different values"
    (is = [:k] (keys-with-different-values {:k "a"} {:k "b"}))
  )
  (spec "complex example"
    (is = [:k1 :k3 :k4] (keys-with-different-values {:k1 :a :k2 :b :k3 1}
                                                    {:k1 :a2 :k2 :b :k4 2}))
  )
)

(spec files-below
  ;; TODO: access the dir where this file lives in so we can make
  ;; the dir ref below location-independent
  (let [testdir (new java.io.File "specs/data/a-directory")]
    (is = (map (fn [f] (.getPath f)) (files-below testdir))
          ["f1" "f2" "a-sub-dir/f3"])
  )
)

;; TODO: to test last-modified-map-below etc we need a way to add and 
;; delete files.