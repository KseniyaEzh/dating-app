const photo = document.getElementById('photo');
const reader = new FileReader();
const imageGrid = document.getElementById('image-grid');

photo.addEventListener('change', (event) => {
    console.log("+++");
    const files = event.target.files;
    const file = files[0];
    reader.readAsDataURL(file);

    reader.addEventListener('load', (event) => {
        const img = document.createElement('img');
        img.src = event.target.result;
        img.alt = file.name;
        imageGrid.appendChild(img);
    });
});