import groovy.xml.MarkupBuilder

def xmlHeader = '<?xml version="1.0" encoding="UTF-8"?>\n'
def xhtmlHeader = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n'
def name = 'Canal' 
def EL = '#{ramoBean.ramo.%s}'
def camelCase = 'canal'
def pasta = 'canal'
def lista = 'canais'
def propriedades = [codigo: String , nome:String]
//p.metaClass.properties.each { if (it.name != 'class') propriedades.put(it.name, it.type) }

def form = new StringWriter()
new MarkupBuilder(form).'ui:composition'(xmlns: 'http://www.w3.org/1999/xhtml',
    'xmlns:f': 'http://java.sun.com/jsf/core',
    'xmlns:h': 'http://java.sun.com/jsf/html',
    'xmlns:p': 'http://primefaces.org/ui',
    'xmlns:ui': 'http://java.sun.com/jsf/facelets',
    template: '/layout/main.xhtml') {
        'ui:define'(name: 'content') {
            'p:panel'(header: 'Criar ' + name) {
            'h:panelGrid'(columns: 2) {
                propriedades.each {
                    'p:outputLabel'('for': it.key, value: it.key)
                    if (it.value == Date)
                        'p:calendar'(id: it.key, value: String.format(EL, it.key), pattern: 'dd/MM/yyyy', showOn: 'button')
                    else if (it.value.getSuperclass() == Number) {
                        'p:spinner'(id: it.key, String.format(EL, it.key))
                    } else if (it.value == Boolean) {
                        'p:selectBooleanCheckbox'(id: it.key, String.format(EL, it.key))
                    } else {
                        'p:inputText'(id: it.key, String.format(EL, it.key))
                    }
                }
            }
            p {
                'p:button'(value: 'Voltar', outcome: '/' + pasta + '/list.html')
                'p:commandButton'(value: 'Salvar', action: '#{' + camelCase + 'Bean.salvar}')
            }
        }
    }
}

def list = new StringWriter()
new MarkupBuilder(list).'ui:composition'(xmlns: 'http://www.w3.org/1999/xhtml',
    'xmlns:f': 'http://java.sun.com/jsf/core',
    'xmlns:h': 'http://java.sun.com/jsf/html',
    'xmlns:p': 'http://primefaces.org/ui',
    'xmlns:ui': 'http://java.sun.com/jsf/facelets',
    template: '/layout/main.xhtml') {
    'ui:define'(name: 'content') {
        'p:panel'(header: 'Listagem de ' + name) {
            p {
                'p:button'(value: 'Criar ' + name, outcome: '/' + pasta + '/form.xhtml')
            }
            'p:dataTable'(id: name.toLowerCase() + 'List',  value: String.format('#{%s.%s}', camelCase + 'Bean', lista), var: name.toLowerCase()
            , emptyMessage: 'Sem registros.') {
                propriedades.each {
                    def attr = it.key
                    'p:column'(headerText: attr) {
                        'h:outputText'(value: String.format('#{%s.%s}', camelCase, attr))
                    }
                }
                'p:column'(width: 85) {
                    'p:commandButton'(icon: 'ui-icon-pencil', action: '/' + pasta + '/form.xhtml') {
                        'f:setPropertyActionListener'(value: '#{' + camelCase + '}', target: '#{' + camelCase + 'Bean.' + camelCase + '}')
                    }
                }
                'p:column'(width: 85) {
                    'p:commandButton'(icon: 'ui-icon-trash', action: '#{' + camelCase + 'Bean.deletar}')
                }
            }
        }
    }
}
println xmlHeader + form.toString().replace('\'', '"')
println ' '