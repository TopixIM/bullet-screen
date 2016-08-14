
(ns bullet-screen.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div span]]
            [bullet-screen.comp.screen :refer [comp-screen]]))

(def style-bg
 {:background-color (hsl 0 0 0),
  :width "1083px",
  :background-image "url(king.jpg)",
  :position "relative",
  :border (str "3px solid " (hsl 0 0 20)),
  :background-size "cover",
  :height "601px"})

(defn render []
  (fn [state mutate!]
    (div
      {:style
       (merge ui/fullscreen ui/center {:background-color (hsl 0 0 0)})}
      (div {:style style-bg} (comp-screen)))))

(def comp-container (create-comp :container render))
