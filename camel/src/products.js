import bean from "./assets/bean.jpg";
import avatar from "./assets/avatar.webp";
import devine from "./assets/devine.jpg";
import elai from "./assets/elai.png";

// Helper function to generate random votes
const generateVoteCount = () => {
    return Math.floor(Math.random() * 100) + 1; // Random number 1-100
};

export const products = [
    {
        id: 1,
        title: 'JOHN ELISH',
        description: 'Action Comedy and Detective.',
        url: '#',
        votes: generateVoteCount(),
        submitterAvatarUrl: avatar,  // Use imported image, not string path
        productImageUrl: bean,       // Use imported image, not string path
    },
    {
        id: 2,
        title: 'PILLOWS',
        description: 'On-demand sand castle construction expertise.',
        url: '#',
        votes: generateVoteCount(),
        submitterAvatarUrl: avatar,  // Use imported image
        productImageUrl: devine,     // Use imported image
    },
    {
        id: 3,
        title: 'THE INCEPTION',
        description: 'SCIFI Action Detective and Thriller.',
        url: '#',
        votes: generateVoteCount(),
        submitterAvatarUrl: avatar,  // Use imported image
        productImageUrl: elai,       // Use imported image
    }
];