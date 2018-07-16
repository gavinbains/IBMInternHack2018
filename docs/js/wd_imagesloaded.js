(function($) {
	var wd_imagesloaded = function(element, callback, when) {
		this.init(element, callback, when);
	};
	
	wd_imagesloaded.prototype = {
		init: function(element, callback, when) {
			this.element = element;
			
			this.reload(callback, when);
		},
		
		reload: function(callback, when) {
			var self = this;
			this.callback = callback;
			this.when = when || "complete";

			if (this.timer)
				clearTimeout(this.timer);
			this.timer = null;
			
			$images = $(this.element).find("img");
			if ($images.length == 0) {
				this.callback.call(this.element);
				return;
			}
			
			this.ready = false;
			this.waiting = $([]);

			$images.each(function() {
				if (!this.complete) {
					var $img = $(this);
					self.waiting = self.waiting.add($img);
					$img.on("load", function() {self.image_done($img);});
					$img.on("error", function() {self.image_done($img);});
				}
			});
			
			if (this.waiting.length > 0)
				$(window).on("load", function() {self.check_done();});
			
			this.ready = true;

			this.check_done(true);
			
			if (this.waiting.length > 0)
				this.timer = setInterval(function() {self.check_done();});
		},
				
		image_done: function($img) {
			this.check_done();
		},
		
		check_done: function(force) {
			var self = this;
			if (this.ready && (this.waiting.length > 0 || force)) {
				var $imgs = $(this.waiting);
				$imgs.each(function() {
					if (this.complete || (self.when == "size" && this.naturalWidth && this.naturalHeight)) {
						self.waiting = self.waiting.not(this);
					}
				});

				if (this.waiting.length == 0) {
					if (this.timer)
						clearTimeout(this.timer);
					this.callback.call(this.element);
				}
			}
		}
	};

	$.fn.wd_imagesloaded = function(callback, when) {
		return this.each(function() {
			var instance = $(this).data('wd_imagesloaded');
			if (instance) {
				instance.reload(callback, when);
			} else {
				$(this).data('wd_imagesloaded', new wd_imagesloaded(this, callback, when));
			}
		});
    };
})(webDriver.jQuery);