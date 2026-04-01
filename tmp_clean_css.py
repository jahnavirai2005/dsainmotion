import re
import os

files = [
    r"c:\Users\jahna\OneDrive\Desktop\project\dsainmotion\src\main\resources\templates\simple-queue-tutorial.html",
    r"c:\Users\jahna\OneDrive\Desktop\project\dsainmotion\src\main\resources\templates\circular-queue-tutorial.html",
    r"c:\Users\jahna\OneDrive\Desktop\project\dsainmotion\src\main\resources\templates\priority-queue-tutorial.html",
    r"c:\Users\jahna\OneDrive\Desktop\project\dsainmotion\src\main\resources\templates\deque-tutorial.html"
]

all_css_lines = []

for file in files:
    with open(file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    start_tag = "<style>"
    end_tag = "</head>"
    
    start_idx = content.find(start_tag)
    end_idx = content.find(end_tag)
    
    if start_idx != -1 and end_idx != -1:
        css_block = content[start_idx + len(start_tag):end_idx].replace("</style>", "").strip()
        all_css_lines.extend([line for line in css_block.split('\n') if line.strip()])
        
        new_content = content[:start_idx] + "\n" + content[end_idx:]
        with open(file, 'w', encoding='utf-8') as f:
            f.write(new_content)

# Deduplicate
seen = set()
unique_css = []
for l in all_css_lines:
    if l in seen: continue
    seen.add(l)
    unique_css.append(l)

styles_file = r"c:\Users\jahna\OneDrive\Desktop\project\dsainmotion\src\main\resources\static\css\styles.css"
with open(styles_file, 'a', encoding='utf-8') as f:
    f.write("\n\n/* ====== QUEUE TUTORIAL STYLES ====== */\n")
    f.write("\n".join(unique_css))
    f.write("\n")
    
print("CSS processing complete. Moved", len(unique_css), "CSS lines to styles.css.")
