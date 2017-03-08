function doAfficheListe(checkboxElem,idDivElem) {
  if (checkboxElem.checked) {
    document.getElementById(idDivElem).style.visibility = "visible"
  } else {
	  document.getElementById(idDivElem).style.visibility = "hidden"
  }
}