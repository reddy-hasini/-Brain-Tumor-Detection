import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

# 🚫 disable absl logging
import absl.logging
absl.logging.set_verbosity(absl.logging.ERROR)

import sys
import numpy as np
import cv2
from tensorflow.keras.models import load_model

# ===== LOAD MODEL (correct path) =====
model = load_model("../model/brain_tumor_model.h5")

# ===== CLASS LABELS =====
classes = ["glioma", "meningioma", "pituitary", "notumor"]

# ===== GET IMAGE PATH FROM JAVA =====
img_path = sys.argv[1]

# ===== READ IMAGE =====
img = cv2.imread(img_path)

if img is None:
    print("Error")
    exit()

# ===== PREPROCESS =====
img_resized = cv2.resize(img, (224, 224))
img_resized = img_resized / 255.0
img_resized = np.reshape(img_resized, (1, 224, 224, 3))

# ===== PREDICTION =====
prediction = model.predict(img_resized, verbose=0)

index = np.argmax(prediction)
result = classes[index]
confidence = prediction[0][index] * 100

# ===== FINAL OUTPUT (ONLY THIS LINE GOES TO JAVA) =====
print(f"{result} ({confidence:.2f}%)")