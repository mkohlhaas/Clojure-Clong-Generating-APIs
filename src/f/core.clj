(ns f.core
  (:require
   [com.phronemophobic.clong.clang   :as clang]
   [com.phronemophobic.clong.gen.jna :as gen])
  (:import
   com.sun.jna.ptr.LongByReference))

(declare zlibVersion compress uncompress)

(def libz (com.sun.jna.NativeLibrary/getInstance "z"))

(def api (clang/easy-api "/usr/include/zlib.h"))

(gen/def-api libz api)

(zlibVersion) ; "1.3.1"

(def source-clong "clong!") ; "clong!"

(comment
  (class source-clong)) ; java.lang.String

(def dest (byte-array 255))
; [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
;  0, 0]

(def dest-len* (doto (LongByReference.)
                 (.setValue (alength dest))))
; #object[com.sun.jna.ptr.LongByReference 0x271c2faf "long@0x7fe1d406fc20=0xff (255)"]

(comment
  (class dest-len*)) ; com.sun.jna.ptr.LongByReference

(compress dest dest-len* source-clong (count source-clong)) ; 0

(.getValue dest-len*) ; 14

(def dest2 (byte-array (count source-clong))) ; [0, 0, 0, 0, 0, 0]

(def dest2-len* (doto (LongByReference.)
                  (.setValue (alength dest2))))
; #object[com.sun.jna.ptr.LongByReference 0x49ab41d4 "long@0x7fe1d400a070=0x6 (6)"]

(uncompress dest2 dest2-len* dest (.getValue dest-len*)) ; 0

(String. dest2) ; "clong!"

(comment
  (class dest2) ; byte/1
  dest2)        ; [99, 108, 111, 110, 103, 33]
