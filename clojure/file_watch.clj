(import '(java.io File))

(defn files-below
  "Returns a lazy seq of the files (excl. dirs) below
   (recursively) file"
  [file]
  (let [subfiles  (.listFiles file)
        onlyfiles (filter (fn [f] (.isFile f)) subfiles)
        onlydirs  (filter (fn [f] (.isDirectory f)) subfiles)]
    (concat onlyfiles (mapcat files-below onlydirs))))

(defn last-modified-map-below
  "Returns a map from files below file to their last modification time"
  [file]
  (reduce (fn [m f] (assoc m f (.lastModified f))) 
          {} (files-below file)))

(defn keys-with-different-values
  "Returns a seq of files that have different values in the maps m1 and m2"
  [m1 m2]
  (filter (fn [k] (not= (k m1) (k m2))) (keys m1)))

(defn files-modified-since
  "Returns a seq of files (recursively) below dir that have been 
   modified compared to modifiedmap"
  [dir modifiedmap]
  (keys-with-different-values (last-modified-map-below dir) modifiedmap))
