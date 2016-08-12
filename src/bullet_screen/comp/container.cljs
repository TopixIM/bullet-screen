
(ns bullet-screen.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div]]))

(defn render [] (fn [state mutate!] (div {})))

(def comp-container (create-comp :container render))

(defonce store-ref (atom {}))

(defonce states-ref (atom {}))
