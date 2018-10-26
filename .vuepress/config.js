module.exports = {
    base: '/core-spring-memo/',
    title: 'Core Spring Memo',
    description: 'Spring Framework 5.x / Spring Boot 2.x',
    dest: 'public',
    ga: 'UA-119599854-1',
    serviceWorker: true,
    themeConfig: {
        nav: [
            { text: 'Home', link: '/' },
            { text: 'このページについて', link: '/about/' },
            { text: 'Springメモ', link: '/contents/' },
            { text: 'ハンズオン', link: '/handson/' }
        ],

        sidebar: {
            '/contents/': genSidebarConfigAbout('Contents')

        },

        lastUpdated: 'Last Updated',

        // Assumes GitHub. Can also be a full GitLab url.
        repo: 'Imamachi-n/core-spring-memo',
        // Customising the header label
        // Defaults to "GitHub"/"GitLab"/"Bitbucket" depending on `themeConfig.repo`
        repoLabel: 'GitHub',

        // Optional options for generating "Edit this page" link

        // if your docs are in a different repo from your main project:
        docsRepo: 'Imamachi-n/core-spring-memo',
        // if your docs are not at the root of the repo:
        docsDir: 'docs',
        // if your docs are in a specific branch (defaults to 'master'):
        docsBranch: 'master',
        // defaults to true, set to false to disable
        editLinks: true,
        // custom text for edit link. Defaults to "Edit this page"
        editLinkText: 'Edit this page on GitHub',


    },
    head: [
        ['link', { rel: 'icon', href: '/hero.jpg' }],
        ['link', { rel: 'manifest', href: '/manifest.json' }],
    ]
}

function genSidebarConfigAbout (title) {
    return [
        {
            title,
            collapsable: false,
            children: [
                '',
                '1_di',
                '2_aop',
                '3_data-management',
                '4_spring-boot',
                '5_mvc',
                '6_security',
                '7_rest',
                '8_testing',
                '9_react'
            ]
        }
    ]
}