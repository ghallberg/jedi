(ns jedi.views
  (:require-macros [cljs.core.async.macros :refer [go]])

  (:require [re-frame.core :as re-frame]
            [reagent.core :as r]
            [chord.client :refer [ws-ch]]
            [cljs.core.async :refer [<!]]))

(def planet (r/atom nil))

(go
  (let [{planet-channel :ws-channel, channel-error :error}
        (<! (ws-ch "ws://localhost:4000" {:format :json-kw}))]
    (if-not channel-error
      (while true
        (let [{planet-info :message, retrieve-error :error}
              (<! planet-channel)]
          (if-not retrieve-error
            (reset! planet planet-info)
            (js/console.log "Uh oh error retrieving:" retrieve-error))))
      (js/console.log "Uh oh error starting:" channel-error))))

(def jedi-slot [jedi]
  [:li.css-slot
   [:h3 (:name jedi)]
   [:h6 "Homeworld: " (planet (:home-planet jedi))]])

(defn main-panel []
  (fn []
    [:div.app-container
     [:div.css-root
      [:h1.css-planet-monitor
       "Obi-Wan is currently on " (str (:name @planet))]
      [:div.css-scrollable-list
       [:ul.css-slots (map @visible-jedis jedi-slot)]
       [:div.css-scroll-buttons
        [:button.css-button-up
         {:on-click #(dispatch [:up-button-clicked])}]
        [:button.css-button-down
         {:on-click #(dispatch [:down-button-clicked])}]]]]]))
