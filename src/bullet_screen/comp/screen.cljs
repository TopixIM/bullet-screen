
(ns bullet-screen.comp.screen
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div input]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.debug :refer [comp-debug]]
            [respo-ui.style :as ui]))

(defn on-input [mutate!]
  (fn [e dispatch!] (mutate! {:text (:value e)})))

(def style-line
 {:line-height 2, :color (hsl 0 0 100), :padding "0 8px"})

(defn update-state [state data] (merge state data))

(defn init-state [& args] {:messages [], :text ""})

(defn on-keydown [state mutate!]
  (fn [e dispatch!]
    (if (= (:key-code e) 13)
      (mutate!
        {:messages
         (conj
           (let [messages (:messages state)]
             (if (> (count messages) 20)
               (subvec messages 10)
               messages))
           (:text state)),
         :text ""}))))

(defn render []
  (fn [state mutate!]
    (div
      {:style (merge ui/column {:height "100%"})}
      (div
        {:style
         (merge ui/flex ui/column {:justify-content "flex-end"})}
        (->>
          (:messages state)
          (map-indexed
            (fn [idx message] [idx
                               (div
                                 {:style style-line}
                                 (comp-text message nil))]))))
      (div
        {:style ui/row}
        (input
          {:style
           (merge
             ui/input
             {:color (hsl 0 0 100),
              :background-color (hsl 0 0 100 0.1),
              :width "400px"}),
           :event
           {:keydown (on-keydown state mutate!),
            :input (on-input mutate!)},
           :attrs {:placeholder "content", :value (:text state)}})))))

(def comp-screen (create-comp :screen init-state update-state render))
