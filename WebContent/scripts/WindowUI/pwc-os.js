// Overide WindowUtilities getPageSize to remove dock height (for maximized windows)
WindowUtilities._oldGetPageSize = WindowUtilities.getPageSize;
WindowUtilities.getPageSize = function() {

  var size = WindowUtilities._oldGetPageSize();
  var dockHeight = $('dock').getHeight();
  
  size.pageHeight -= dockHeight;
  size.windowHeight -= dockHeight;
  return size;
  
};    


// Overide Windows minimize to move window inside dock  
Object.extend(Windows, {

  // Overide minimize function
  minimize: function(id, event) {
    var win = this.getWindow(id);
    var winTitle = win.getTitle();
    if (win && win.visible) {
      // Hide current window
      win.hide();            
    
      // Create a dock element
      var element = document.createElement("span");
      element.className = "dock_icon"; 
      element.style.display = "none";
      element.win = win;
      $('dock').appendChild(element);
      Event.observe(element, "mouseup", Windows.restore);
      //$(element).update(winTitle);
      $(element).update(win.getTitle());
    
      new Effect.Appear(element)
    }
    Event.stop(event);
  },
                   
  // Restore function
  restore: function(event) { 
  
    var element = Event.element(event);
    
    element.win.show();
    Windows.focus(element.win.getId());                    
    element.win.toFront();
    element.remove();
    
    // Fade and destroy icon
    new Effect.Fade(element, {afterFinish: function() {element.remove()}})
    
  }
})

// blur focused window if click on document
Event.observe(document, "click", function(event) {   
  var e = Event.element(event);
  var win = e.up(".dialog");
  var dock = e == $('dock') || e.up("#dock"); 
  if (!win && !dock && Windows.focusedWindow) {
    Windows.blur(Windows.focusedWindow.getId());                    
  }
})               
