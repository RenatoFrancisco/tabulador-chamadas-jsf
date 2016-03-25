function back() {
	PF('wizard').back();
	alert('Teste efetuado!');
}

function resetWizard() {
	wizard.loadStep(wiz.cfg.steps[0], true);
}
