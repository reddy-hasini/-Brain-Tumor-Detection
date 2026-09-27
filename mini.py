import sys

# Get image path from Java
image_path = sys.argv[1]

# Dummy logic (replace with your ML model later)
if "tumor" in image_path.lower():
    print("Tumor Detected")
else:
    print("No Tumor")