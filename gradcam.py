import numpy as np
import cv2
import tensorflow as tf
from tensorflow.keras.models import load_model
import matplotlib.pyplot as plt

# 1. Setup and Load
model = load_model("model/brain_tumor_model.h5")
img_path = "C:/Users/GAYATHRI/Downloads/mini/Te-me_36.jpg"
classes = ["glioma", "meningioma", "pituitary", "notumor"]

def get_gradcam_heatmap(img_array, model, last_conv_layer_name, pred_index=None):
    # Create a model that maps the input image to the activations of the last conv layer
    grad_model = tf.keras.models.Model(
        [model.inputs], [model.get_layer(last_conv_layer_name).output, model.output]
    )

    with tf.GradientTape() as tape:
        last_conv_layer_output, preds = grad_model(img_array)
        if pred_index is None:
            pred_index = tf.argmax(preds[0])
        class_channel = preds[:, pred_index]

    # Gradient of the output class with respect to the output feature map
    grads = tape.gradient(class_channel, last_conv_layer_output)

    # Mean intensity of the gradient over a specific feature map channel
    pooled_grads = tf.reduce_mean(grads, axis=(0, 1, 2))

    # Multiply each channel in the feature map by "how important this channel is"
    last_conv_layer_output = last_conv_layer_output[0]
    heatmap = last_conv_layer_output @ pooled_grads[..., tf.newaxis]
    heatmap = tf.squeeze(heatmap)

    # Normalize the heatmap between 0 & 1
    heatmap = tf.maximum(heatmap, 0) / tf.math.reduce_max(heatmap)
    return heatmap.numpy()

# 2. Preprocess Image
img = cv2.imread(img_path)
img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
img_resized = cv2.resize(img_rgb, (224, 224))
img_array = np.expand_dims(img_resized / 255.0, axis=0)

# 3. Generate Heatmap 
# For MobileNetV2, the last conv layer is usually 'out_relu'
last_conv_layer = "out_relu" 
heatmap = get_gradcam_heatmap(img_array, model, last_conv_layer)

# 4. Superimpose Heatmap on Image
heatmap = cv2.resize(heatmap, (img.shape[1], img.shape[0]))
heatmap = np.uint8(255 * heatmap)
heatmap = cv2.applyColorMap(heatmap, cv2.COLORMAP_JET)

# Combine heatmap with original image (0.4 opacity for heatmap)
superimposed_img = cv2.addWeighted(img, 0.6, heatmap, 0.4, 0)

# 5. Display Result
cv2.imshow("Brain Tumor Heatmap", superimposed_img)
cv2.waitKey(0)
cv2.destroyAllWindows()