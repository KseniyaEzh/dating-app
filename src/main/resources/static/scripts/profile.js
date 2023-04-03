const photo = document.getElementById('photo');
const reader = new FileReader();
const imageGrid = document.getElementById('image-grid');
// const img = document.getElementById('ava');

photo.addEventListener('change', (event) => {
    const files = event.target.files;
    const file = files[0];
    reader.readAsDataURL(file);

    reader.addEventListener('load', (event) => {
        // const img = document.createElement('img');
        const image = document.getElementById('ava');
        image.src = event.target.result;
        image.alt = file.name;
        // img.style.width = '150px';
        // img.style.height = '150px';
        // imageGrid.appendChild(img);
    });
});