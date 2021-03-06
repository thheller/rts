(ns ^:figwheel-always game.client.gamma_ext
  (:refer-clojure
    :exclude [aget])
  (:require
    [gamma.ast :as ast]
    gamma.compiler.core
    fipp.engine
    [gamma.emit.emit :as emit]
    gamma.emit.fun
    gamma.emit.operator
    gamma.emit.statement
    gamma.emit.tag
    gamma.emit.construct
    [gamma.api :as g]))

(defn part [v i]
  (g/aget v i))

(defn map-over-vec4 [f v]
  (apply g/vec4 (for [i (range 4)] (f (part v i)))))

(defn map-over-vec2 [f v]
  (apply g/vec2 (for [i (range 2)] (f (part v i)))))

(defn x [v]
  (g/swizzle v :x))

(defn y [v]
  (g/swizzle v :y))

(defn z [v]
  (g/swizzle v :z))

(defn w [v]
  (g/swizzle v :w))

; (defn collection-element-type [x]
;   ({:vec4 :float :vec3 :float :vec2 :float
;     :ivec4 :int :ivec3 :int :ivec2 :int
;     :bvec4 :bool :bvec3 :bool :bvec2 :bool} x))
;
; (defn aget [x i]
;   (let [t (ast/term :aget x i)]
;     (assoc
;      t
;      :type (collection-element-type (:type (first (:body t)))))))

(defn aget [x i]
  (g/aget x (g/int i)))

(defn if-statement [c a b]
  (assoc
    (ast/term :if c
             (ast/term :block a)
             (ast/term :block b))
    :type nil))
