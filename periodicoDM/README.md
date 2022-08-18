<h2>Trabajo practico final INFORMATORIO 2022</h2>
<hr>
<h5>Objetivo:</h5>
<p>El Informatorio planea lanzar en varias plataformas (Web, iOS y 
Android) una aplicación donde se publicaran varios articulos de 
noticias. Los articulos pueden pertenecer a diferentes fuentes 
(periodicos, blogs,etc) y la autoria de cada uno es por un autor (solo 
uno).
Los articulos que proveera la API por defecto son solo los publicados.</p>

<h5>Entidades:</h5>
<p>El <b>ARTICULO</b> debe presentar los siguientes atributos:</p>
<ul>
<li>id (autogenerado)</li>
<li>title: Representa el titulo</li>
<li>description: Breve descripcion de la noticia</li>
<li>url: Link hacia la pagina de la noticia (ej:https://www.infobae.com/america/ciencia-america/2022/07/12/en-vivo-la-nasa)</li>
<li>urlToImage: Link de la imagen de portada (ej:https://www.infobae.com/new-resizer/4q_cPUh59XY.jpg)</li>
<li>publishedAt: Fecha de publicacion</li>
<li>content: Texto completo del contenido de la noticia</li>
<li>Author: Relacion con Author</li>
<li>Source: Relacion con Fuente de la noticia</li>
</ul>

<p>La <b>FUENTE</b> debe presentar los siguientes atributos:</p>
<ul>
<li>id (autogenerado)</li>
<li>name: Nombre de la Fuente (ejemplo: Infobae)</li>
<li>code: Representa el nombre con un patron (ej: Si el nombre es: La Nacion, el code sera la-nacion) de solo minusculas 
y los espacios en blanco se reemplazan con el simbolo -</li>
<li>contenido: Cuerpo de la publicación</li>
<li>createdAt: Fecha de creacion</li>
</ul>

<p>El <b>AUTOR</b> debe presentar los siguientes atributos:</p>
<ul>
<li>id (autogenerado)</li>
<li>firstname: Primer Nombre</li>
<li>lastname: Apellido</li>
<li>fullname: Nombre completo (firstname + apellido)</li>
<li>createdAt: Fecha de creacion</li>
</ul>